import { Checking } from '../../models/checking';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { User } from '../../models/user';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UsersService } from 'src/app/services/users.service';
import { trigger, style, transition, animate, state } from '@angular/animations';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-checking-card',
  templateUrl: './checking-card.component.html',
  styleUrls: ['./checking-card.component.css'],
  animations: [
    trigger('enterState', [
      state('void', style({
        transform: 'translateX-(-100%)',
        opacity: 0
      })),
      transition(':enter', [
        animate(3000, style ({
          transform: 'translateX(0)',
          opacity:1
        }))
      ])
    ])

  ]
})
export class CheckingCardComponent implements OnInit {

  checkings: Checking[] = []
  checkingId!: number;
  checkingBalance!: number;
  checkingSecretKey!: string;
  checkingUserId!: number;
  checkingCurrency!: string;
  checkingMinimumBalance!: number;
  checkingStatus!:string;
  checking!: Checking;
  currentUser!: User;

  constructor(private userService: UsersService, private token: TokenStorageService) { }

  ngOnInit(): void {

    this.currentUser = this.token.getUser();
    this.getCheckingsById();
  }

  getCheckings(): void {

    this.checkings = [];
    this.userService.getCheckings().subscribe(result => {
      for(let i=0; i< result.length ;i++){
        this.checkingId = result[i].id;
        this.checkingBalance = result[i].balance.amount;
        this.checkingCurrency = result[i].balance.currency;
        this.checkingUserId = result[i].user.id;
        this.checkingSecretKey = result[i].secretKey;
        this.checkingStatus = result[i].status;
        this.checkingMinimumBalance = result[i].minimumBalance;
        this.checkings.push(new Checking (this.checkingBalance, this.checkingCurrency, this.checkingUserId, this.checkingSecretKey, this.checkingMinimumBalance, this.checkingStatus, this.checkingId))
      }
      
      this.checkingId = 0;
      this.checkingBalance = 0;
      this.checkingCurrency ="";
      this.checkingUserId = 0;
      this.checkingSecretKey = "";
      this.checkingMinimumBalance = 0;
      this.checkingStatus = "";
      this.checkingMinimumBalance = 0;
    });
  }  

   getCheckingsById(): void{
    this.userService.getCheckingsById(this.currentUser.id).subscribe(result =>{
      for(let i=0; i< result.length;i++){
        this.checkingId = result[i].id;
        this.checkingBalance = result[i].balance.amount;
        this.checkingCurrency = result[i].balance.currency;
        this.checkingUserId = result[i].user.id;
        this.checkingSecretKey = result[i].secretKey;
        this.checkingMinimumBalance = result[i].minimumBalance;
        this.checkings.push(new Checking (this.checkingBalance, this.checkingCurrency, this.checkingUserId, this.checkingSecretKey, this.checkingMinimumBalance, this.checkingStatus, this.checkingId))
      }

      this.checkingBalance = 0;
      this.checkingCurrency ="";
      this.checkingUserId = 0;
      this.checkingSecretKey = "";
      this.checkingMinimumBalance = 0;
      this.checkingStatus = "";
      this.checkingMinimumBalance = 0;
    });
  } 
}

