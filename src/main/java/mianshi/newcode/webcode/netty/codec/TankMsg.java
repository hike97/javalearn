package mianshi.newwebcode.netty.codec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: hike97
 * @createTime: 2023/04/04 10:06
 * @description: 坦克消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TankMsg {
    int x , y;
}
