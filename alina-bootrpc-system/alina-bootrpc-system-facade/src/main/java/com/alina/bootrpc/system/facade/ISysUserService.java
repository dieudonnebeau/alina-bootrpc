package com.alina.bootrpc.system.facade;

import com.alina.bootrpc.common.core.exception.BusinessException;
import com.alina.bootrpc.common.mapper.service.IBaseService;
import com.alina.bootrpc.system.dto.SysUserDTO;
import com.alina.bootrpc.system.model.SysUser;

import java.util.List;

/**
 * @author     ：迪艾多
 * @date       ：Created on 2019/9/20 15:20
 * @description：系统用户服务接口
 * @modified By：
 * @version:     1.0
 */
public interface ISysUserService extends IBaseService<SysUser> {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUser selectUserByLoginName(String userName);

    /**
     * 通过手机号码查询用户
     *
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    SysUser selectUserByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    SysUser selectUserByEmail(String email);

    /**
     * 查询用户所属角色组
     * @param userId 用户ID
     * @return 结果
     */
    String selectUserRoleGroup(Long userId);

    /**
     * 查询用户所属岗位组
     * @param userId 用户ID
     * @return 结果
     */
    String selectUserPostGroup(Long userId);

    /**
     * 根据条件分页查询已分配用户角色列表
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 根据条件分页查询未分配用户角色列表
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 导入用户数据
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName);

    /**
     * 校验登录名称是否唯一
     * @param loginName 用户名
     * @return
     */
    String checkLoginNameUnique(String loginName);

    /**
     * 校验用户名称是否唯一
     * @param user 用户信息
     * @return
     */
    String checkPhoneUnique(SysUser user);

    /**
     * 校验email是否唯一
     * @param user 用户信息
     * @return
     */
    String checkEmailUnique(SysUser user);

    /**
     * 新增保存用户信息
     *
     * @param userDTO 用户信息
     * @return 结果
     */
    Integer insertUser(SysUserDTO userDTO);

    /**
     * 修改保存用户信息
     *
     * @param userDTO 用户信息
     * @return 结果
     */
    Integer updateUser(SysUserDTO userDTO);

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Integer deleteUserByIds(String ids) throws BusinessException ;

}
