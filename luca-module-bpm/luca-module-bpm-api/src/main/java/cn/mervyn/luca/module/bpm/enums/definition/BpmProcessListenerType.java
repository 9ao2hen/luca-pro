package cn.mervyn.luca.module.bpm.enums.definition;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * BPM 流程监听器的类型
 *
 * @author 9ao2hen
 */
@Getter
@AllArgsConstructor
public enum BpmProcessListenerType {

    EXECUTION("execution", "执行监听器"),
    TASK("task", "任务执行器");

    private final String type;
    private final String name;

}
