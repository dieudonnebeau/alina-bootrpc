package com.alina.bootrpc.common.mapper.service;

import com.alina.bootrpc.common.core.entity.response.ResultResponseEntity;
import com.alina.bootrpc.common.core.entity.response.StateAndMsgResponseEntity;
import com.alina.bootrpc.common.mapper.util.PageUtil;

import java.util.List;
import java.util.Map;


/**
 * @author     ：迪艾多
 * @date       ：Created in ${DATE} ${TIME}
 * @description：通用底层接口
 * @modified By：
 * @version:   1.0
 */
public interface  IBaseService<T> {

	/**
	 * 
	 * @Title: save   
	 * @Description: 新增entity   
	 * @param: @param entity
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int save(T entity);
	

	/**
	 * 
	 * @Title: insertNotNull   
	 * @Description: 新增entity(不包含空字段)  
	 * @param: @param entity
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int saveNotNull(T entity);
	/**
	 * 
	 * @Title: updateByID   
	 * @Description: 更新entity(包含空字段)   
	 * @param: @param entity
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int updateByID(T entity);
	
	/**
	 * 
	 * @Title: updateByIDNotNull   
	 * @Description: 更新entity(不包含空字段)   
	 * @param: @param entity
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int updateByIDNotNull(T entity);

	/**
	 * 
	 * @Title: deleteByID   
	 * @Description: 根据主键删除   
	 * @param: @param key
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int deleteByID(Object key);
	
	/**
	 * 
	 * @Title: deleteByExample   
	 * @Description: 根据Example删除     
	 * @param: @param example
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int deleteByExample(Object example);
	
	/**
	 * 
	 * @Title: queryByID   
	 * @Description: 根据ID获取   
	 * @param: @param key
	 * @param: @return      
	 * @return: T      
	 * @throws
	 */
	T queryByID(Object key);
	

	/**
	 * 
	 * @Title: queryList   
	 * @Description: 查询所有记录列表 (有条件)   
	 * @param: @param entity
	 * @param: @return      
	 * @return: List<T>      
	 * @throws
	 */
	List<T> queryList(T entity);

	/**
	 * 
	 * @Title: queryOne   
	 * @Description: 查询单条记录     
	 * @param: @param entity
	 * @param: @return      
	 * @return: T      
	 * @throws
	 */
	T queryOne(T entity);
	
	/**
	 * 
	 * @Title: queryOneByExample   
	 * @Description: 查询单条记录  
	 * @param: @param example
	 * @param: @return      
	 * @return: T      
	 * @throws
	 */
	T queryOneByExample(Object example);

	/**
	 *
	 * @Title: queryOneByParams
	 * @Description: 查询单条记录
	 * @param: @param example
	 * @param: @return
	 * @return: T
	 * @throws
	 */
	T queryOneByParams(Class clazz,  Map<String , Object> params);
	
	/**
	 * 
	 * @Title: queryCount   
	 * @Description: count   
	 * @param: @param entity
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	int queryCount(T entity) ;

	/**
	 * 
	 * @Title: queryByExample   
	 * @Description: 查询所有记录列表 (有条件)   
	 * @param: @param example
	 * @param: @return      
	 * @return: List<T>      
	 * @throws
	 */
	List<T> queryByExample(Object example);
	
	/**
	 * 
	 * @Title: queryAll   
	 * @Description: 查询所有记录列表 (无条件)
	 * @param: @return      
	 * @return: List<T>      
	 * @throws
	 */
	List<T> queryAll();
	
	/**
	 * 
	 * @Title: queryPage   
	 * @Description: 所有数据PageUtil对象
	 * @param: @param page
	 * @param: @param entity
	 * @param: @return      
	 * @retrn: PageUtil<T>
	 * @throws
	 */
	PageUtil <T> queryPage(PageUtil<T> page, T entity) ;

	/**
	 * 
	 * @Title: queryByParams   
	 * @Description: 根据条件example查询PageUtil对象   
	 * @param: @param page
	 * @param: @param example
	 * @param: @return      
	 * @return: PageUtil<T>      
	 * @throws
	 */
	PageUtil<T> queryByExample(PageUtil<T> page, Object example) ;
	

	/**
	 * 
	 * @Title: commonSave   
	 * @Description: 通用新增entity方法  
	 * @param: @param entity
	 * @param: @return      
	 * @return: ResultResponseEntity<StateAndMsgResponseEntity>      
	 * @throws
	 */
	ResultResponseEntity<StateAndMsgResponseEntity> commonSave(T entity);
	
	/**
	 * 
	 * @Title: commonSave   
	 * @Description: 通用新增entity方法 (不包含空字符串)
	 * @param: @param entity
	 * @param: @return      
	 * @return: ResultResponseEntity<StateAndMsgResponseEntity>      
	 * @throws
	 */
	ResultResponseEntity<StateAndMsgResponseEntity> commonSaveNotNull(T entity);
	
	/**
	 * 
	 * @Title: commonUpdate   
	 * @Description: 通用根据主键更新entity方法    
	 * @param: @param entity
	 * @param: @return      
	 * @return: ResultResponseEntity<StateAndMsgResponseEntity>      
	 * @throws
	 */
	ResultResponseEntity<StateAndMsgResponseEntity> commonUpdate(T entity);
	
	/**
	 * 
	 * @Title: commonUpdate   
	 * @Description: 通用根据主键更新entity方法    
	 * @param: @param entity
	 * @param: @return      
	 * @return: ResultResponseEntity<StateAndMsgResponseEntity>      
	 * @throws
	 */
	ResultResponseEntity<StateAndMsgResponseEntity> commonUpdateNotNull(T entity);
	
	/**
	 * 
	 * @Title: commonDeleteByID   
	 * @Description: 通用删除方法   
	 * @param: @param keyID
	 * @param: @return      
	 * @return: ResultResponseEntity<StateAndMsgResponseEntity>      
	 * @throws
	 */
	ResultResponseEntity<StateAndMsgResponseEntity> commonDeleteByID(Object keyID);
	
	/**
	 * 
	 * @Title: commonQueryPage   
	 * @Description: 所有数据ResultResponseEntity<PageUtil<T>>通用对象
	 * @param: @param page
	 * @param: @param entity
	 * @param: @return      
	 * @return: ResultResponseEntity<PageUtil<T>>    
	 * @throws
	 */
	ResultResponseEntity<PageUtil<T>> commonQueryPage(PageUtil<T> page, T entity);
	
	/**
	 * 
	 * @Title: commonQueryByParams   
	 * @Description: 根据条件example通用查询ResultResponseEntity<PageUtil<T>>对象   
	 * @param: @param page
	 * @param: @param example
	 * @param: @return      
	 * @return: ResultResponseEntity<PageUtil<T>>      
	 * @throws
	 */
	ResultResponseEntity<PageUtil<T>>  commonQueryByParams(PageUtil<T> page, Object example);

}
