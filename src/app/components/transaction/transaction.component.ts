import { transition } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/models/transaction';
import { User } from 'src/app/models/user';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  currentUser!: User;
  transaction!: Transaction;
  inputOrigenAccountId!: number;
  inputDestinationAccountId!: number;
  inputDescription!: string;
  inputAmount!: number;
  inputNameOwnerDestinationAccount!: string;
  isSuccessful = false;

  constructor(private userService: UsersService, private token: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }

  createTransaction(): void {

    const transaction: Transaction = new Transaction (
     this.inputOrigenAccountId,
     this.inputDestinationAccountId,
     this.inputDescription,
     this.inputAmount,
     this.inputNameOwnerDestinationAccount,
      );

    this.userService.getAccountById(this.inputOrigenAccountId).subscribe(result => {
      console.log(result.user.id)
    if (result.user.id == this.currentUser.id){
      let response = this.userService.createTransaction(transaction);
      console.log(response);
      alert("Transaction done!");
      }else{
        alert("You must be the owner of the origen account")
      }
    });
  }   
}



  

