import { Component, OnInit } from '@angular/core';
import { Saving } from 'src/app/models/saving';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-create-saving',
  templateUrl: './create-saving.component.html',
  styleUrls: ['./create-saving.component.css']
})
export class CreateSavingComponent implements OnInit {

  constructor(private adminService: AdminService) { }

  
  inputBalanceAmount!: number;
  inputBalanceCurrency!: string;
  inputUserId!: number;
  inputSecretKey!: string;
  inputStatus!: string;
  inputMinimumBalance!: number;
  inputInterestRate!:number;

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  ngOnInit(): void {
  }


  async createNewSaving(): Promise<void> {
    const saving: Saving = new Saving(
      this.inputBalanceAmount,
      this.inputBalanceCurrency,
      this.inputUserId,
      this.inputSecretKey,
      this.inputStatus,
      this.inputMinimumBalance,
      this.inputInterestRate,
      );

    if(!(this.inputMinimumBalance == null) && this.inputMinimumBalance >1000) {
          alert ("Minimum Balance can not be greater than 1000")
    } 

    if(!(this.inputMinimumBalance == null) && this.inputMinimumBalance< 100) {
      alert ("Minimum Balance must be greater than 100")
    } 
   
    console.log(saving);
    let response = await this.adminService.createSaving(saving);
    console.log(response);
    alert("Saving created! Interest Rate is always 0.1");
  }
}
