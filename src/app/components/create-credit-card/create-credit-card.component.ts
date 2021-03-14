import { Component, OnInit } from '@angular/core';
import { CreditCard } from 'src/app/models/credit-card';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-create-credit-card',
  templateUrl: './create-credit-card.component.html',
  styleUrls: ['./create-credit-card.component.css']
})
export class CreateCreditCardComponent implements OnInit {

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
  }

  inputBalanceAmount!: number;
  inputBalanceCurrency!: string;
  inputUserId!: number;
  inputCreditLimit!:number;
  inputInterestRate!:number;

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  async createNewCreditCard(): Promise<void> {
    const creditCard: CreditCard = new CreditCard(
      this.inputBalanceAmount,
      this.inputBalanceCurrency,
      this.inputUserId,
      this.inputCreditLimit,
      this.inputInterestRate,
      );

    if(!(this.inputCreditLimit == null) && this.inputCreditLimit < 100){
      alert("Credit Limit must be greater than 100")
    }
    
    if(!(this.inputCreditLimit == null) && this.inputCreditLimit > 100000){
      alert("Credit Limit can not be greater than 100000")
    } 

    if(!(this.inputInterestRate == null) && this.inputInterestRate < 0.1){
      alert("Interest must be greater than 0.1")
    } 

    if(!(this.inputInterestRate == null) && this.inputInterestRate > 0.2){
      alert("Interest can not be greater than 0.2")
    } 
   
    let response = await this.adminService.createCreditCard(creditCard);
    console.log(response);
    alert("Credit Card created!");
  }

}
