package com.kbtg.hackathon.fruitmark;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbtg.hackathon.fruitmark.dao.MerchantRepository;
import com.kbtg.hackathon.fruitmark.entity.Merchant;

@Controller
@RequestMapping(path="/") 
public class DBConnector {

	@Autowired 
	MerchantRepository merchantDao;
	
	public static void main(String[] args) {
		DBConnector con = new DBConnector();
		con.findAll();
		//System.out.println(con.findAll());
	}
	
	
	@GetMapping(path="/")	
	public void findAll() {
		try {
			System.out.println("> Find All:"+ merchantDao.findById(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return merchantDao.findById(1);
	}

}
