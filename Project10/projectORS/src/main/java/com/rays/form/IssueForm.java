package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.IssueDTO;

import com.rays.validation.ValidDate;

public class IssueForm extends BaseForm {
	@Size(max = 50, message = "title should not exceed 50 characters")
	@Pattern(regexp = "^[A-Za-z]+([ '-][A-Za-z]+)*$", message = "title contains alphabets only")
	@NotEmpty(message = "Title is required")
	private String title;

	@Size(max = 50, message = "Description should not exceed 50 characters")
	@Pattern(regexp = "^[A-Za-z]+([ '-][A-Za-z]+)*$", message = "description contains alphabets only")
	@NotEmpty(message = "Description is required")
	private String description;

	@Size(max = 25, message = "assignTo should not exceed 50 characters")
	@Pattern(regexp = "^[A-Za-z]+([ '-][A-Za-z]+)*$", message = "assignTo contains alphabets only")
	@NotEmpty(message = "Assign To is required")
	private String assignTo;

	@Size(max = 20, message = "status should not exceed 50 characters")
	@Pattern(regexp = "^[A-Za-z]+([ '-][A-Za-z]+)*$", message = "status contains alphabets only")
	@NotEmpty(message = "Status is required")
	private String status;

	@NotNull(message = "Please enter open date")
	@ValidDate(message = "Invalid date format or value")
	private String openDate;

	@Override
	public BaseDTO getDto() {
		IssueDTO dto = initDTO(new IssueDTO());
		dto.setTitle(title);
		dto.setDescription(description);
		dto.setAssignTo(assignTo);
		dto.setStatus(status);

		if (openDate != null && !openDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(openDate);
				dto.setOpenDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}
		return dto;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
}
