import { AdminService } from 'src/app/services/admin.service';
import { Component, Input, OnInit, Output } from '@angular/core';
import { User } from 'src/app/models/user';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UsersService } from 'src/app/services/users.service';
import { Account } from './../../models/account';
import { Status } from 'src/app/models/status';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  currentUser!: User;
  user!: User;
  accounts: Account[] = []
  accountId!: number;
  accountBalance!: number;
  accountCurrency!: string;
  accountSecretKey!: string;
  accountUserId!: number;
  account!: Account;
  clicked = false;
  status!: Status;
  inputStatus!: string;

  constructor(private userService: UsersService, private token: TokenStorageService, private adminService: AdminService) { }

  ngOnInit(): void {

    this.currentUser = this.token.getUser();
    this.getAllAccounts();
    
  }

  getAllAccounts(): void {
    this.accounts = [];
    this.adminService.getAllAccounts().subscribe(result => {
      for(let i=0; i< result.length ;i++){
        this.accountId = result[i].id;
        this.accountBalance = result[i].balance.amount;
        this.accountCurrency = result[i].balance.currency;
        this.accountUserId = result[i].user.id;
        this.accountSecretKey = result[i].secretKey;
        this.accounts.push(new Account (this.accountBalance, this.accountCurrency, this.accountUserId, this.accountSecretKey, this.accountId))
      }

        this.accountId = 0;
        this.accountBalance = 0;
        this.accountCurrency = "";
        this.accountUserId = 0;
        this.accountSecretKey = "";
    });
  }  

  getAccountsById(): void {
    this.accounts = [];
    this.userService.getAccountsById(this.currentUser.id).subscribe(result => {
      for(let i=0; i< result.length ;i++){
        this.accountId = result[i].id;
        this.accountBalance = result[i].balance.amount;
        this.accountCurrency = result[i].balance.currency;
        this.accountUserId = result[i].user.id;
        this.accountSecretKey = result[i].secretKey;
        this.accounts.push(new Account (this.accountBalance, this.accountCurrency, this.accountUserId, this.accountSecretKey, this.accountId))
      }

        this.accountId = 0;
        this.accountBalance = 0;
        this.accountCurrency = "";
        this.accountUserId = 0;
        this.accountSecretKey = "";
    });
  } 
  
  delete(accountId:number){

    this.accounts.splice(accountId-1, 1)
    this.adminService.deleteAccount(accountId);
  }
 
/*   updateStatus(accountId: number){
     console.log(this.inputStatus);
     const status: string =  (
     'ACTIVE'
   );
     let result = this.adminService.updateStatus(accountId, status);
    } */

  }


