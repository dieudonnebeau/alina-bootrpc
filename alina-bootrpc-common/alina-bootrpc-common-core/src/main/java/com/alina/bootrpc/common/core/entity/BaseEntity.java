package com.alina.bootrpc.common.core.entity;

public class BaseEntity<T> {

	/**
	 * The empty {@code BaseEntity}, with no body.
	 */
	public static final BaseEntity<?> EMPTY = new BaseEntity<Object>();

	private final T obj;

	/**
	 * Create a new, empty {@code BaseEntity}.
	 */
	protected BaseEntity() {
		this(null);
	}

	/**
	 * Create a new {@code BaseEntity} with the given body and no headers.
	 * 
	 * @param body
	 *            the entity body
	 */
	public BaseEntity(T obj) {
		this.obj = obj;
	}

	/**
	 * Returns the body of this entity.
	 */
	public T getObj() {
		return this.obj;
	}

	/**
	 * Indicates whether this entity has a body.
	 */
	public boolean hasObj() {
		return (this.obj != null);
	}

}