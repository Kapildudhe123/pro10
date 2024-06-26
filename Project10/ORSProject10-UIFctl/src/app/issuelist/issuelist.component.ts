import { Component } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-issuelist',
  templateUrl: './issuelist.component.html',
  styleUrls: ['./issuelist.component.css']
})
export class IssuelistComponent extends BaseListCtl {
  
  isValidTitleInput: boolean = true;
  isValidDescriptionInput: boolean = true;
  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.ISSUE, locator, route);
  }

  validateInput(event: any, field: string) {
    const value = event.target.value;
    
    if (field === 'title') {
      const containsNonAlphabetic = /[^\sa-zA-Z]/.test(value); // Check for non-alphabetic and non-space characters
      if (containsNonAlphabetic) {
        console.log('Please enter letters and spaces only.');
        this.isValidTitleInput = false;
      } else {
        this.isValidTitleInput = true;
      }
    } if (field === 'description') {
      const containsNonAlphabetic = /[^\sa-zA-Z]/.test(value); // Check for non-alphabetic and non-space characters
      if (containsNonAlphabetic) {
        console.log('Please enter letters and spaces only.');
        this.isValidDescriptionInput = false;
      } else {
        this.isValidDescriptionInput = true;
      }
    }
  }
}