package com.vulinh.service.scheduler;

import module java.base;

import com.vulinh.springcron.SpringCronAdapter;
import com.vulinh.springcron.input.SpringCronInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerCron {

  public String newPostEventCron() {
    var expression =
        SpringCronInput.builder()
            .secondInput(SpringCronAdapter.everyNSecondsMinutes(30))
            .hourInput(SpringCronAdapter.betweenHours(8, 18))
            .weekDayInput(SpringCronAdapter.betweenWeekDays(DayOfWeek.MONDAY, DayOfWeek.FRIDAY))
            .build()
            .toExpression();

    log.info(
        "Expression: {} | Next execution for new post event: {}",
        expression,
        CronExpression.parse(expression).next(LocalDateTime.now()));

    return expression;
  }
}
