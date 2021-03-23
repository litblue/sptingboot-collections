package cn.litblue.service.impl;

import cn.litblue.model.entity.Role;
import cn.litblue.mapper.RoleMapper;
import cn.litblue.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litblue
 * @since 2020-12-22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
