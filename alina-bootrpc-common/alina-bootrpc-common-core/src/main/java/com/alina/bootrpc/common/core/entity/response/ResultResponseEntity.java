package com.alina.bootrpc.common.core.entity.response;


import com.alina.bootrpc.common.core.entity.BaseEntity;
import com.alina.bootrpc.common.core.enums.ResultState;

import java.io.Serializable;

public class ResultResponseEntity<T>  extends BaseEntity<T> implements Serializable{
	public static final long serialVersionUID = 8642211936743995448L;
	private final Object state;


	/**
	 * Create a new {@code ResultEntity} with the given status code, and no obj.
	 * @param state the status code
	 */
	public ResultResponseEntity(ResultState state) {
		this.state = state;
	}
	
	/**
	 * Create a new {@code HttpEntity} with the given obj,and status code.
	 * @param body the entity obj
	 * @param state the status body
	 */
	public ResultResponseEntity(T body, ResultState state) {
		super(body);
		this.state = state;
	}

	/**
	 * Create a new {@code HttpEntity} with the given body, and status code.
	 * Just used behind the nested builder API.
	 * @param body the entity body
	 */
	private ResultResponseEntity(T body, Object state) {
		super(body);
		this.state = state;
	}


	/**
	 * Return the HTTP status code of the response.
	 * @return the HTTP status as an ResultStatus enum entry
	 */
	public ResultState getMessage() {
		if (this.state instanceof ResultState) {
			return (ResultState) this.state;
		}
		else {
			return ResultState.valueOf((Integer) this.state);
		}
	}

	/**
	 * Return the HTTP status code of the response.
	 * @return the HTTP status as an int value
	 * @since 4.3
	 */
	public Integer getState() {
		if (this.state instanceof ResultState) {
			return ((ResultState) this.state).getState();
		}
		else {
			return (Integer) this.state;
		}
	}


	// Static builder methods

	/**
	 * Create a builder with the given status.
	 * @param state the response status
	 * @return the created builder
	 * @since 4.1
	 */
	public static BodyBuilder status(ResultState state) {
		return new DefaultBuilder(state);
	}

	/**
	 * Create a builder with the given status.
	 * @param state the response status
	 * @return the created builder
	 * @since 4.1
	 */
	public static BodyBuilder status(int state) {
		return new DefaultBuilder(state);
	}

	/**
	 * Create a builder with the status set to {@linkplain ResultState#SUCCESS SUCCESS}.
	 * @return the created builder
	 * @since 4.1
	 */
	public static BodyBuilder success() {
		return status(ResultState.SUCCESS);
	}

	/**
	 * A shortcut for creating a {@code ResultEntity} with the given body and
	 * the status set to {@linkplain ResultState#SUCCESS SUCCESS}.
	 * @return the created {@code ResultEntity}
	 * @since 4.1
	 */
	public static <T> ResultResponseEntity<T> success(T obj) {
		BodyBuilder builder = success();
		return builder.body(obj);
	}
	
	/**
	 * Create a builder with the status set to {@linkplain ResultState#FAIL FAIL}.
	 * @return the created builder
	 * @since 4.1
	 */
	public static BodyBuilder fail() {
		return status(ResultState.FAIL);
	}

	/**
	 * A shortcut for creating a {@code ResultEntity} with the given body and
	 * the status set to {@linkplain ResultState#FAIL FAIL}.
	 * @return the created {@code ResultEntity}
	 * @since 4.1
	 */
	public static <T> ResultResponseEntity<T> fail(T obj) {
		BodyBuilder builder = fail();
		return builder.body(obj);
	}
	
	/**
	 * Create a builder with the status set to {@linkplain ResultState#SYSTEM_EXCEPTION SYSTEM_EXCEPTION}.
	 * @return the created builder
	 * @since 4.1
	 */
	public static BodyBuilder systemException() {
		return status(ResultState.SYSTEM_EXCEPTION);
	}

	/**
	 * A shortcut for creating a {@code ResultEntity} with the given body and
	 * the status set to {@linkplain ResultState#SYSTEM_EXCEPTION SYSTEM_EXCEPTION}.
	 * @return the created {@code ResultEntity}
	 * @since 4.1
	 */
	public static <T> ResultResponseEntity<T> systemException(T obj) {
		BodyBuilder builder = systemException();
		return builder.body(obj);
	}


	/**
	 * Defines a builder that adds a body to the response entity.
	 * @since 4.1
	 */
	public interface BodyBuilder{
		
		/**
		 * Build the response entity with no body.
		 * @return the response entity
		 * @see BodyBuilder#body(Object)
		 */
		ResultResponseEntity<Void> build();

		/**
		 * Set the body of the response entity and returns it.
		 * @param <T> the type of the body
		 * @param body the body of the response entity
		 * @return the built response entity
		 */
		<T> ResultResponseEntity<T> body(T body);
	}


	private static class DefaultBuilder implements BodyBuilder {

		private final Object state;


		public DefaultBuilder(Object state) {
			this.state = state;
		}

		@Override
		public ResultResponseEntity<Void> build() {
			return body(null);
		}

		@Override
		public <T> ResultResponseEntity<T> body(T obj) {
			return new ResultResponseEntity<T>(obj, this.state);
		}
	}

}