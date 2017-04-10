package com.lawcloud.lawper.controller;

import com.github.pagehelper.PageInfo;
import com.lawcloud.lawper.common.controller.BashController;
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

    @RequestMapping(value = {"list", "index", "index.html", ""})
    public ModelAndView getList(Country country,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "10") int rows) {
        ModelAndView result = new ModelAndView(page_list);
        List<Country> countryList = countryService.selectByCountry(country, page, rows);
        result.addObject("pageInfo", new PageInfo<Country>(countryList));
        result.addObject("queryParam", country);
        result.addObject("page", page);
        result.addObject("rows", rows);
        return result;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView view(Country country) {
        ModelAndView result = new ModelAndView();
        if (country.getId() != null) {
            country = countryService.selectByKey(country.getId());
        }
        result.addObject("country", country);
        return result;
    }

    /**
     * 保存或修改country信息
     *
     * @param country
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(Country country) {
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
        countryService.delete(id);
        return redirect_list;
    }

}
