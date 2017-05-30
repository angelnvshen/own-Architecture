package com.lawcloud.lawper.controller;

import com.github.pagehelper.PageInfo;
import com.lawcloud.lawper.model.Country;
import com.lawcloud.lawper.service.CountryService;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *
 */
@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private EhCacheFactoryBean ehCacheFactoryBean;

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

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(Country country) {
        ModelAndView result = new ModelAndView(redirect_list);
        if (country.getId() != null) {
            countryService.updateAll(country);
        } else {
            countryService.save(country);
        }
        return result;
    }

    @RequestMapping("delete")
    public String delete(Integer id) {
        countryService.delete(id);
        return redirect_list;
    }

    @ResponseBody
    @RequestMapping("ehcache")
    public String ehcacheTest(Country country){

        country.setId(country.getId());
        country.setCountrycode(country.getCountrycode());
        country.setCountryname(country.getCountryname());
        System.out.println("get country from cache ");
        Ehcache cache = ehCacheFactoryBean.getObject();
        Element tmp = cache.get(country.getId());
        Country countryTmp = null;
        if (tmp != null)
            countryTmp = (Country) tmp.getObjectValue();
        System.out.println(countryTmp);
        Element element = new Element(country.getId(), country);
        System.out.println("put country from cache ");
        cache.put(element);
        return country.toString();
    }

}
