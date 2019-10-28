package com.alina.bootrpc.common.mapper.service.impl;


import com.alina.bootrpc.common.core.entity.response.ResultResponseEntity;
import com.alina.bootrpc.common.core.entity.response.StateAndMsgResponseEntity;
import com.alina.bootrpc.common.core.enums.CommonEnum;
import com.alina.bootrpc.common.core.utils.BlankUtil;
import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.common.mapper.util.PageUtil;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> implements IBaseService<T> {
	@Autowired
	protected Mapper<T> mapper;

	public Mapper<T> getMapper() {
		return mapper;
	}

	@Override
	public int save(T entity) {
		return mapper.insert(entity);
	}


	@Override
	public int saveNotNull(T entity) {
		return mapper.insertSelective(entity);
	}
	@Override
	public int updateByID(T entity) {
		return mapper.updateByPrimaryKey(entity);
	}
	@Override
	public int updateByIDNotNull(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}


	@Override
	public int deleteByID(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int deleteByIDS(String ids) {
		int result = 0;
		if(BlankUtil.isNotBlank(ids)){
			String [] array = ids.split(",");
			for (int i = 0 ; i < array.length ; i++){
				result += this.deleteByID(array[i]);
			}

		}
		return result;
	}

	@Override
	public int deleteByExample(Object example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public T queryByID(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public List<T> queryList(T entity) {
		return mapper.select(entity);
	}
	@Override
	public T queryOne(T entity) {
		return mapper.selectOne(entity);
	}
	@Override
	public T queryOneByExample(Object example) {
		List<T> list = queryByExample(example);
		if(BlankUtil.isBlank(list)) {
			return null;
		}else {
			return list.get(0);
		}
	}

	@Override
	public T queryOneByParams(Class clazz , Map<String , Object> params){
		Example example = new Example(clazz);
		Example.Criteria criteria = example.createCriteria();
		for(Map.Entry<String, Object> entry : params.entrySet()){
			criteria.andEqualTo(entry.getKey(),entry.getValue());
		}
		return  queryOneByExample(example);
	}

	@Override
	public List<T> queryListByParams(Class clazz , Map<String , Object> params){
		Example example = new Example(clazz);
		Example.Criteria criteria = example.createCriteria();
		for(Map.Entry<String, Object> entry : params.entrySet()){
			criteria.andEqualTo(entry.getKey(),entry.getValue());
		}
		return  queryByExample(example);
	}

	@Override
	public int queryCount(T entity) {
		return mapper.selectCount(entity);
	}
	@Override
	public List<T> queryByExample(Object example) {
		return mapper.selectByExample(example);
	}
	@Override
	public List<T> queryAll() {
		return mapper.selectAll();
	}

	@Override
	public PageUtil<T> queryPage(PageUtil<T> page, T entity) {
		if(BlankUtil.isBlank(page.getOrderBy())){
			PageMethod.startPage(page.getPageNo(), page.getPageSize());
		}else{
			PageMethod.startPage(page.getPageNo(), page.getPageSize(),page.getOrderBy());
		}
		List<T> list = null;
		if(BlankUtil.isBlank(entity)){
			list = queryAll();
		}else{
			list = queryList(entity);
		}

		return new PageUtil<T>(list);
	}

	@Override
	public PageUtil<T> queryByExample(PageUtil<T> page, Object example) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<T> list = queryByExample(example);
		return new PageUtil<T>(list);
	}


	@Override
	public ResultResponseEntity<StateAndMsgResponseEntity> commonSave(T entity) {
		int result = save(entity);
		if (result <= 0) {
			return ResultResponseEntity.fail(
					new StateAndMsgResponseEntity(CommonEnum.SAVE_FAIL.getState(), CommonEnum.SAVE_FAIL.getMessage()));
		} else {
			return ResultResponseEntity.success(
					new StateAndMsgResponseEntity(CommonEnum.SAVE_SUCCESS.getState(), CommonEnum.SAVE_SUCCESS.getMessage()));
		}
	}

	@Override
	public ResultResponseEntity<StateAndMsgResponseEntity> commonSaveNotNull(T entity) {
		int result = saveNotNull(entity);
		if (result <= 0) {
			return ResultResponseEntity.fail(
					new StateAndMsgResponseEntity(CommonEnum.SAVE_FAIL.getState(), CommonEnum.SAVE_FAIL.getMessage()));
		} else {
			return ResultResponseEntity.success(
					new StateAndMsgResponseEntity(CommonEnum.SAVE_SUCCESS.getState(), CommonEnum.SAVE_SUCCESS.getMessage()));
		}
	}
	
	@Override
	public ResultResponseEntity<StateAndMsgResponseEntity> commonUpdate(T entity) {
		int result = updateByID(entity);
		if (result <= 0) {
			return ResultResponseEntity.fail(
					new StateAndMsgResponseEntity(CommonEnum.UPDATE_FAIL.getState(), CommonEnum.UPDATE_FAIL.getMessage()));
		} else {
			return ResultResponseEntity.success(
					new StateAndMsgResponseEntity(CommonEnum.UPDATE_SUCCESS.getState(), CommonEnum.UPDATE_SUCCESS.getMessage()));
		}
	}

	@Override
	public ResultResponseEntity<StateAndMsgResponseEntity> commonUpdateNotNull(T entity) {
		int result = updateByIDNotNull(entity);
		if (result <= 0) {
			return ResultResponseEntity.fail(
					new StateAndMsgResponseEntity(CommonEnum.UPDATE_FAIL.getState(), CommonEnum.UPDATE_FAIL.getMessage()));
		} else {
			return ResultResponseEntity.success(
					new StateAndMsgResponseEntity(CommonEnum.UPDATE_SUCCESS.getState(), CommonEnum.UPDATE_SUCCESS.getMessage()));
		}
	}

	@Override
	public ResultResponseEntity<StateAndMsgResponseEntity> commonDeleteByID(Object keyID) {
		int result = deleteByID(keyID);
		if (result <= 0) {
			return ResultResponseEntity.fail(
					new StateAndMsgResponseEntity(CommonEnum.DELETE_FAIL.getState(), CommonEnum.DELETE_FAIL.getMessage()));
		} else {
			return ResultResponseEntity.success(
					new StateAndMsgResponseEntity(CommonEnum.DELETE_SUCCESS.getState(), CommonEnum.DELETE_SUCCESS.getMessage()));
		}
	}

	@Override
	public ResultResponseEntity<PageUtil<T>> commonQueryPage(PageUtil<T> page, T entity) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<T> list = queryList(entity);
		return ResultResponseEntity.success((new PageUtil<T>(list)));
	}

	@Override
	public ResultResponseEntity<PageUtil<T>>  commonQueryByParams(PageUtil<T> page, Object example) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<T> list = queryByExample(example);
		return ResultResponseEntity.success((new PageUtil<T>(list)));
	}

}
