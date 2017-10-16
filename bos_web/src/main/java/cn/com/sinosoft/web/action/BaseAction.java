package cn.com.sinosoft.web.action;

import cn.com.sinosoft.utils.pageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	protected pageBean pageBean =  new pageBean();
	public void setPage(Integer page) {
		pageBean.setCurrentPage(page);
	}
	public void setRows(Integer rows) {
		pageBean.setPageSize(rows);
	}
	protected T model;//new User();

	@Override
	public T getModel() {
		return model;
	}

	public BaseAction(){
		//UserAction extends BaseAction<User>
		//UserAction userAction = new UserAction();//spring工厂<bean id="userAction" class="...UserAction">
		//this == userAction;
		//this.getClass() == UserAction.class
		//this.getClass().getGenericSuperclass() == BaseAction<User>.class
		ParameterizedType parameterizedType = (ParameterizedType) 
					this.getClass().getGenericSuperclass();
		//parameterizedType.getActualTypeArguments() == [User.class]
		Type[] types = parameterizedType.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) types[0];//User.class
		//使用反射创建实例
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);// 离线查询条件，存放查询条件的变量
		pageBean.setDc(dc);
		try {
			model = entityClass.newInstance();//new User();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void writeObject2Json(Object obj, String[] excludes) {
		// 3.将pageBean转换成json，通过response返回到前台
		// JSONObject:用来将对象转换成json
		// JSONArray:用来将集合（数组、list、set、map）转换成json
		// JsonConfig:用来排除不需要转换成json的属性
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONObject jsonObject = JSONObject.fromObject(obj, jsonConfig);
		String json = jsonObject.toString();
		// 通过response将json返回到前台
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeList2Json(List<?> list, String[] excludes) {
		// 3.将list转换成json，通过response返回到前台
		// JSONObject:用来将对象转换成json
		// JSONArray:用来将集合（数组、list、set、map）转换成json
		// JsonConfig:用来排除不需要转换成json的属性
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		String json = jsonArray.toString();
		// 通过response将json返回到前台
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
