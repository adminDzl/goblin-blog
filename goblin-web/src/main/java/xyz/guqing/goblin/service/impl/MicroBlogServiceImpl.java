package xyz.guqing.goblin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xyz.guqing.goblin.common.CustomException;
import xyz.guqing.goblin.common.PageResult;
import xyz.guqing.goblin.entity.*;
import xyz.guqing.goblin.mapper.MicroblogCommentMapper;
import xyz.guqing.goblin.mapper.MicroblogMapper;
import xyz.guqing.goblin.mapper.UserMapper;
import xyz.guqing.goblin.service.MicroBlogService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MicroBlogServiceImpl implements MicroBlogService {
    @Resource
    private MicroblogMapper microblogMapper;
    @Resource
    private MicroblogCommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;
    
    @Override
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor= CustomException.class)
    public void save(Microblog microblog) {
        microblog.setCreateTime(new Date());
        microblog.setHeartCount(0);
        microblog.setShareCount(0);
        microblog.setVisitCount(0);
        microblog.setIsOpen(1);
        microblog.setModifyTime(microblog.getCreateTime());
        microblogMapper.insertSelective(microblog);
    }
    
    @Override
    public PageResult queryAll(Integer currentPage, Integer pageSize) {
        //将分页设置给分页器
        PageHelper.startPage(currentPage, pageSize);
        return getPageResult();
    }
    
    private PageResult getPageResult() {
        List<Microblog> microblogList = microblogMapper.selectByExample(null);
        
        List<MicroBlogVO> microBlogVOList = new ArrayList<>();
        for(Microblog microblog : microblogList){
            MicroBlogVO microBlogVO = new MicroBlogVO();
            
            microBlogVO.setMicroblog(microblog);
            List<MicroblogComment> commentList = this.queryComment(microblog.getId());
            microBlogVO.getComment().addAll(commentList);
           
            microBlogVOList.add(microBlogVO);
        }
        
        PageInfo<MicroBlogVO> pageInfo = new PageInfo<MicroBlogVO>(microBlogVOList);
        
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }
    
    /**
     * 查询用户评论
     * @param mid 动态（微博）id
     * @return 返回评论集合
     */
    private List<MicroblogComment> queryComment(Integer mid){
        MicroblogCommentExample example = new MicroblogCommentExample();
        MicroblogCommentExample.Criteria criteria = example.createCriteria();
        criteria.andMidEqualTo(mid);
        List<MicroblogComment> commentList = commentMapper.selectByExample(example);
        for(MicroblogComment comment : commentList){
            String username = this.getUsernameById(comment.getUid());
            comment.setUsername(username);
        }
       return commentList;
    }
    
    private String getUsernameById(Integer uid){
        User user = userMapper.selectByPrimaryKey(uid);
        return user.getUsername();
    }
}
