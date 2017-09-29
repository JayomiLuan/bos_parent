package cn.com.sinosoft.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
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
		try {
			model = entityClass.newInstance();//new User();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
