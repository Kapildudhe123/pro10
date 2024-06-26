package com.rays.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.LeadDTO;

@Repository
public class LeadDAOImpl extends BaseDAOImpl<LeadDTO> implements LeadDAOInt {

	@Override
	protected List<Predicate> getWhereClause(LeadDTO dto, CriteriaBuilder builder, Root<LeadDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getContacName())) {

			whereCondition.add(builder.like(qRoot.get("contacName"), dto.getContacName() + "%"));
		}

		if (!isZeroNumber(dto.getMobile())) {

			whereCondition.add(builder.equal(qRoot.get("mobile"), dto.getMobile()));
		}

		if (!isEmptyString(dto.getStatus())) {

			whereCondition.add(builder.like(qRoot.get("status"), dto.getStatus()));
		}

		if (isNotNull(dto.getDate())) {
			// Assuming "registrationDate" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getDate();

			// Define start and end dates for the search day
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(searchDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0); // Start of the day
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date startDate = calendar.getTime();

			calendar.set(Calendar.HOUR_OF_DAY, 23); // End of the day
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			Date endDate = calendar.getTime();

			// Create predicate for date range
			Predicate datePredicate = builder.between(qRoot.get("date"), startDate, endDate);
			whereCondition.add(datePredicate);
		}
		return whereCondition;

	}

	@Override
	public Class<LeadDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return LeadDTO.class;
	}

}
