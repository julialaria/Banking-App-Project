import { Account } from './../../models/account';
import { Checking } from './../../models/checking';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UsersService } from 'src/app/services/users.service';
import { Transaction } from 'src/app/models/transaction';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {

  content?: string;
  currentUser!: User;
  user!: User;
  accounts: Account[] = []
  accountId!: number;
  accountBalance!: number;
  accountCurrency!: string;
  accountSecretKey!: string;
  accountUserId!: number;
  accountIsDisplayed:boolean = false;
  account!: Account;
  clicked = false;

  constructor(private userService: UsersService, private token: TokenStorageService) { }

  ngOnInit(): void {

    this.currentUser = this.token.getUser();
    this.getBalance();
    
  }

  getBalance(): void {
    this.userService.getBalanceById(this.currentUser.id).subscribe(result=> this.accountBalance = result
     );
  }   
}

