package com.kbtg.hackathon.fruitmark.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kbtg.hackathon.fruitmark.entity.Merchant;
import com.kbtg.hackathon.fruitmark.service.MerchantService;

@Repository
public class MerchantDaoImpl implements MerchantService {
	
	@Override
	public List<Merchant> listMerchant() {
		List<Merchant> result = new ArrayList<>();
		result.add(new Merchant(1, "Name", "LinkImg", "LinkCover", 1, new Timestamp(0)));
		return result;// getNamedParameterJdbcTemplate().query(SQL_QUERY_BY_IDS, Collections.singletonMap("ids", ids), new GroupDomainMapping());
	}
	
}
