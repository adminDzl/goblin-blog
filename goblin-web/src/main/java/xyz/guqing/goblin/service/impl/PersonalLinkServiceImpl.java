package xyz.guqing.goblin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import xyz.guqing.goblin.common.PageResult;
import xyz.guqing.goblin.entity.BlogArticle;
import xyz.guqing.goblin.entity.PersonalLink;
import xyz.guqing.goblin.mapper.PersonalLinkMapper;
import xyz.guqing.goblin.service.PersonalLinkService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class PersonalLinkServiceImpl implements PersonalLinkService {
    @Resource
    private PersonalLinkMapper linkMapper;
    
    @Override
    public void save(PersonalLink personalLink) throws Exception {
        personalLink.setCreateTime(new Date());
        personalLink.setModifyTime(personalLink.getCreateTime());
        linkMapper.insertSelective(personalLink);
    }
    
    @Override
    public PersonalLink getById(Integer id) throws Exception{
        return linkMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public PageResult queryAll(Integer currentPage, Integer pageSize) throws Exception {
        //将分页设置给分页器
        PageHelper.startPage(currentPage, pageSize);
        List<PersonalLink> personalLinkList = linkMapper.selectByExample(null);
        
        PageInfo<PersonalLink> pageInfo = new PageInfo<PersonalLink>(personalLinkList);
    
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }
    
    @Override
    public void update(PersonalLink personalLink) throws Exception {
        personalLink.setModifyTime(new Date());
        linkMapper.updateByPrimaryKeySelective(personalLink);
    }
    
    @Override
    public void delete(Integer id) throws Exception {
        linkMapper.deleteByPrimaryKey(id);
    }
}
