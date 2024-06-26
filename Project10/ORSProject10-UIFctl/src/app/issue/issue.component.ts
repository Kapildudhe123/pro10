import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BaseCtl } from '../base.component';
import { ServiceLocatorService } from '../service-locator.service';

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent extends BaseCtl {

  constructor(
    public locator: ServiceLocatorService,
    public route: ActivatedRoute,
    private httpClient: HttpClient
  ) {
    super(locator.endpoints.ISSUE, locator, route);
  }

  validateForm(form: any): boolean {
    let flag = true;
    let validator = this.serviceLocator.dataValidator;

    flag = flag && validator.isNotNullObject(form.title);
    flag = flag && validator.isNotNullObject(form.openDate);
    flag = flag && validator.isNotNullObject(form.assignTo);
    flag = flag && validator.isNotNullObject(form.description);
    flag = flag && validator.isNotNullObject(form.status);

    return flag;
  }

  populateForm(form: any, data: any): void {
    form.id = data.id;
    form.title = data.title;
    form.openDate = data.openDate;
    form.description = data.description;
    form.status = data.status;
    form.assignTo = data.assignTo;
  }

  parseDate(dateString: string): Date {
    return dateString ? new Date(dateString) : null;
  }
}
