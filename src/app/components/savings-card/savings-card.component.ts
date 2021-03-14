import { Component, OnInit } from '@angular/core';
import { Saving } from 'src/app/models/saving';
import { User } from 'src/app/models/user';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-savings-card',
  templateUrl: './savings-card.component.html',
  styleUrls: ['./savings-card.component.css']
})
export class SavingsCardComponent implements OnInit {
  
  savings: Saving[] = []
  savingsId!: number;
  savingsBalance!: number;
  savingsSecretKey!: string;
  savingsUser!: number;
  savingsCurrency!: string;
  savingsMinimumBalance!: number;
  savingsInterestRate!: number;
  savingsStatus!: string;
  saving!: Saving;
  currentUser!: User;

  constructor(private userService: UsersService, private token: TokenStorageService) { }

  ngOnInit(): void {

    this.currentUser = this.token.getUser();
    this.getSavingsById();
  }


  getSavingsById(): void{

    this.userService.getSavingsById(this.currentUser.id).subscribe(result =>{
      for(let i=0; i< result.length;i++){
        this.savingsId = result[i].id;
        this.savingsBalance = result[i].balance.amount;
        this.savingsCurrency = result[i].balance.currency;
        this.savingsUser = result[i].user.id;
        this.savingsSecretKey = result[i].secretKey;
        this.savingsStatus = result[i].status;
        this.savingsMinimumBalance = result[i].minimumBalance;
        this.savingsInterestRate = result[i].interestRate;
      
        this.savings.push(new Saving (this.savingsBalance, this.savingsCurrency, this.savingsUser,this.savingsSecretKey, this.savingsStatus, this.savingsMinimumBalance, this.savingsInterestRate, this.savingsId))
      }

      this.savingsId = 0;
      this.savingsBalance = 0;
      this.savingsCurrency = "";
      this.savingsUser = 0;
      this.savingsSecretKey = "";
      this.savingsStatus ="";
      this.savingsMinimumBalance =0;
      this.savingsInterestRate = 0;
    });

  }

}
