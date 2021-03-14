import { CreditCard } from './../../models/credit-card';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UsersService } from 'src/app/services/users.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-credit-card-card',
  templateUrl: './credit-card-card.component.html',
  styleUrls: ['./credit-card-card.component.css']
})
export class CreditCardCardComponent implements OnInit {

  creditcards: CreditCard[] = []
  creditCardId!: number;
  creditCardBalance!: number;
  creditCardSecretKey!: string;
  creditCardUserId!: number;
  creditCardCurrency!: string;
  creditCardCreditLimit!: number;
  creditCardInterestRate!: number;
  CreditCard!: CreditCard;
  currentUser!: User;


  constructor(private userService: UsersService, private token: TokenStorageService) { }

  ngOnInit(): void {

    this.currentUser = this.token.getUser();
    this.getCreditCardsById();
  }

  getCreditCardsById(): void{

    this.userService.getCreditCardsById(this.currentUser.id).subscribe(result =>{
      for(let i=0; i< result.length;i++){
        this.creditCardId = result[i].id;
        this.creditCardBalance = result[i].balance.amount;
        this.creditCardCurrency = result[i].balance.currency;
        this.creditCardUserId = result[i].user.id;
        this.creditCardCreditLimit = result[i].creditLimit;
        this.creditCardInterestRate = result[i].interestRate;
        this.creditcards.push(new CreditCard (this.creditCardBalance, this.creditCardCurrency, this.creditCardUserId, this.creditCardCreditLimit, this.creditCardInterestRate, this.creditCardId))
      }

      this.creditCardId = 0;
      this.creditCardBalance = 0;
      this.creditCardCurrency ="";
      this.creditCardSecretKey = "";
      this.creditCardUserId = 0;
      this.creditCardCreditLimit = 0;
      this.creditCardInterestRate =0;
    });

  }
}
