/**
 * 
 */
package com.meila.meigou.dbhelper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meila.meigou.dbhelper.db.StudentEntity;
import com.meila.meigou.dbhelper.db.StudentEntityMapper;
import com.meila.meigou.dbhelper.db.TeacherEntity;
import com.meila.meigou.dbhelper.db.TeacherEntityMapper;

/**
 ************************************************************
 * @类名 : ServiceTest.java
 *
 * @DESCRIPTION :
 * @AUTHOR : flong
 * @DATE : 2015年11月9日
 ************************************************************
 */
@Service
public class StudentService {
    @Autowired
    private StudentEntityMapper mapper;
    @Autowired
    private TeacherEntityMapper teacherEntityMapper;

    // @Master
    public StudentEntity get(int id) {
        return mapper.selectByPrimaryKey(id);
    }

    // @Slave
    public void update(StudentEntity entity) {
        mapper.updateByPrimaryKey(entity);
    }

    public void delete(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("fff");
        }

    }

    public void transaction(int id) throws Exception {
        // 先查询
        System.out.println("start");
        StudentEntity entity = mapper.selectByPrimaryKey(id);
        System.out.println("select 1:" + entity.getName());
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId(1);
        teacherEntity.setName("new");
        teacherEntityMapper.updateByPrimaryKey(teacherEntity);
        System.out.println("update:" + teacherEntity.getName());
        // delete(-100);// 异常回滚
        entity = mapper.selectByPrimaryKey(id);
        System.out.println("select 2:" + entity.getName());
    }
}
