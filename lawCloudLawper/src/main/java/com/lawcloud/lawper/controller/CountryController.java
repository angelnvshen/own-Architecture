package com.lawcloud.lawper.controller;

import com.github.pagehelper.PageInfo;
import com.lawcloud.lawper.common.base.controller.BashController;
import com.lawcloud.lawper.model.Country;
import com.lawcloud.lawper.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * country 增删查改
 */
@Controller
public class CountryController extends BashController {

    @Autowired
    private CountryService countryService;

    private String page_list = "index";

    private String redirect_list = "redirect:list";

    /**
     * 开始页面
     *
     * @param country 参数
     * @param page    页数
     * @param rows    行数
     * @return 返回页面和其他信息
     */
    @RequestMapping(value = {"list", "index", "index.html", ""})
    public ModelAndView getList(Country country,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "10") int rows) {

        log.info("getList : " + country);

        ModelAndView result = new ModelAndView(page_list);
        try {
            List<Country> countryList = countryService.selectByCountry(country, page, rows);
            result.addObject("pageInfo", new PageInfo<Country>(countryList));
            result.addObject("queryParam", country);
            result.addObject("page", page);
            result.addObject("rows", rows);
        }catch (Exception e){
            e.printStackTrace();
            log.error("error : " + e.getMessage());
            result.addObject("errorMessage", e.getMessage());
        }
        return result;
    }

    /**
     * 视图
     *
     * @param country 参数
     * @return 返回页面和其他信息
     */
    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView view(Country country) {

        log.info("view : " + country);

        ModelAndView result = new ModelAndView();
        try {
            if (country.getId() != null) {
                country = countryService.selectByKey(country.getId());
            }
            result.addObject("country", country);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error : " + e.getMessage());
            result.addObject("errorMessage", e.getMessage());
        }
        return result;
    }

    /**
     * 保存或修改country信息
     *
     * @param country 参数
     * @return 返回页面和其他信息
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(Country country) {

        log.info("save : " + country);

        ModelAndView result = new ModelAndView(redirect_list);
        //设置默认查询信息
        result.addObject("message", "fail");
        try {
            int resultValue;
            if (country.getId() != null) {
                resultValue = countryService.updateAll(country);
            } else {
                resultValue = countryService.save(country);
            }

            //resultValue 大于0时，说明操作成功
            if (resultValue >= 0)
                result.addObject("message", "success");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("error : " + e);
            result.addObject("message", e.getMessage());
        }
        return result;
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public String delete(Integer id) {
        log.info("delete : " + id);
        try {
            countryService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error : " + e);
            return " to error page , 页面需要自定义 ";
        }
        return redirect_list;
    }

}
