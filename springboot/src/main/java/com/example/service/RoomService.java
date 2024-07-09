package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.enums.RoomStatusEnum;
import com.example.entity.Account;
import com.example.entity.Room;
import com.example.mapper.RoomMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 房间信息表业务处理
 **/
@Service
public class RoomService {

    @Resource
    private RoomMapper roomMapper;

    /**
     * 新增
     */
    public void add(Room room) {
        room.setStatus(RoomStatusEnum.ROOM_OK.status);
        roomMapper.insert(room);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        roomMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            roomMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Room room) {
        roomMapper.updateById(room);
    }

    /**
     * 根据ID查询
     */
    public Room selectById(Integer id) {
        return roomMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Room> selectAll(Room room) {
        return roomMapper.selectAll(room);
    }

    /**
     * 分页查询
     */
    public PageInfo<Room> selectPage(Room room, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Room> list = roomMapper.selectAll(room);
        return PageInfo.of(list);
    }

    public List<Room> selectAllIdle(Room room) {
        room.setStatus(RoomStatusEnum.ROOM_OK.status);
        return roomMapper.selectAll(room);
    }
}