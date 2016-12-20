package com.bill.coder;

import com.bill.domain.app.App;

/**
 * Created by huangbin on 2016/12/16.
 * <p>
 * 可智能编码接口
 */
public interface SmartCodeable {


    /**
     * 根据模板属性创建模板
     *
     * @param app
     * @return 返回生成结果
     */
    Boolean createFile(App app);


}
