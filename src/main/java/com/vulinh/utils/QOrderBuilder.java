package com.vulinh.utils;

import module java.base;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.OrderSpecifier.NullHandling;

/**
 * Small helper to build {@link OrderSpecifier} instances for a given Querydsl {@link Expression}.
 *
 * <p>Provides convenient factory methods for ascending/descending order with explicit nulls
 * handling. When not specified, the default nulls placement is {@link
 * OrderSpecifier.NullHandling#NullsLast}.
 *
 * @param <T> value type of the expression; must be {@link Comparable}
 */
public final class QOrderBuilder<T extends Comparable<? super T>> {

  /** Default nulls handling when not explicitly specified. */
  private static final NullHandling DEFAULT_NULL_HANDLING = NullHandling.NullsLast;

  /** The expression to order by. */
  private final Expression<T> field;

  /**
   * Creates a new builder.
   *
   * @param field the expression to order by; must not be {@code null}
   * @throws NullPointerException if {@code field} is {@code null}
   */
  private QOrderBuilder(Expression<T> field) {
    this.field = Objects.requireNonNull(field, "field");
  }

  /**
   * Static factory method.
   *
   * @param <T> value type of the expression
   * @param field the expression to order by; must not be {@code null}
   * @return a new {@code QOrderBuilder} for the provided expression
   * @throws NullPointerException if {@code field} is {@code null}
   */
  public static <T extends Comparable<? super T>> QOrderBuilder<T> of(Expression<T> field) {
    return new QOrderBuilder<>(field);
  }

  /**
   * Ascending order using the default nulls handling ({@link #DEFAULT_NULL_HANDLING}).
   *
   * @return ascending {@link OrderSpecifier} with default nulls placement
   */
  public OrderSpecifier<T> asc() {
    return order(Order.ASC, DEFAULT_NULL_HANDLING);
  }

  /**
   * Descending order using the default nulls handling ({@link #DEFAULT_NULL_HANDLING}).
   *
   * @return descending {@link OrderSpecifier} with default nulls placement
   */
  public OrderSpecifier<T> desc() {
    return order(Order.DESC, DEFAULT_NULL_HANDLING);
  }

  /**
   * Ascending order with null values placed first.
   *
   * @return ascending {@link OrderSpecifier} with {@link NullHandling#NullsFirst}
   */
  public OrderSpecifier<T> ascNullFirst() {
    return order(Order.ASC, NullHandling.NullsFirst);
  }

  /**
   * Ascending order with null values placed last.
   *
   * @return ascending {@link OrderSpecifier} with {@link NullHandling#NullsLast}
   */
  public OrderSpecifier<T> ascNullLast() {
    return order(Order.ASC, NullHandling.NullsLast);
  }

  /**
   * Descending order with null values placed first.
   *
   * @return descending {@link OrderSpecifier} with {@link NullHandling#NullsFirst}
   */
  public OrderSpecifier<T> descNullFirst() {
    return order(Order.DESC, NullHandling.NullsFirst);
  }

  /**
   * Descending order with null values placed last.
   *
   * @return descending {@link OrderSpecifier} with {@link NullHandling#NullsLast}
   */
  public OrderSpecifier<T> descNullLast() {
    return order(Order.DESC, NullHandling.NullsLast);
  }

  private OrderSpecifier<T> order(Order direction, NullHandling nulls) {
    return new OrderSpecifier<>(direction, field, nulls);
  }
}
