package cn.mervyn.luca.module.promotion.dal.mysql.article;

import cn.mervyn.luca.framework.common.pojo.PageResult;
import cn.mervyn.luca.framework.mybatis.core.mapper.BaseMapperX;
import cn.mervyn.luca.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.mervyn.luca.module.promotion.controller.admin.article.vo.category.ArticleCategoryPageReqVO;
import cn.mervyn.luca.module.promotion.dal.dataobject.article.ArticleCategoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章分类 Mapper
 *
 * @author HUIHUI
 */
@Mapper
public interface ArticleCategoryMapper extends BaseMapperX<ArticleCategoryDO> {

    default PageResult<ArticleCategoryDO> selectPage(ArticleCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ArticleCategoryDO>()
                .likeIfPresent(ArticleCategoryDO::getName, reqVO.getName())
                .eqIfPresent(ArticleCategoryDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ArticleCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ArticleCategoryDO::getSort));
    }

    default List<ArticleCategoryDO> selectListByStatus(Integer status) {
        return selectList(ArticleCategoryDO::getStatus, status);
    }

}
