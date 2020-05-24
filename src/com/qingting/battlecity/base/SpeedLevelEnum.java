package com.qingting.battlecity.base;

/**
 * @Author: qingting
 * @Description: 速度等级枚举
 * @Create: 2020/5/23 19:12
 */
public enum SpeedLevelEnum {

    LEVEL_ONE(1),
    LEVEL_TWO(2),
    LEVEL_THREE(3),
    LEVEL_FOUR(4),
    LEVEL_FIVE(5),
    LEVEL_SIX(6),
    LEVEL_SEVEN(7),
    LEVEL_EIGHT(8),
    LEVEL_NINE(9);

    private int level;

    SpeedLevelEnum(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
