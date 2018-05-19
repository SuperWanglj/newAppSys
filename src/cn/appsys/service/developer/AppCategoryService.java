package cn.appsys.service.developer;

import java.util.List;
import cn.appsys.pojo.AppCategory;

public interface AppCategoryService {
	/**
	 * 根据父节点parentId获取相应的分类列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<AppCategory> getAppCategoryListByParentId(Integer parentId)throws Exception;
}
