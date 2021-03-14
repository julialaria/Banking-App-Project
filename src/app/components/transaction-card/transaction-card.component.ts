import { AdminService } from 'src/app/services/admin.service';
import { transition } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/models/transaction';

@Component({
  selector: 'app-transaction-card',
  templateUrl: './transaction-card.component.html',
  styleUrls: ['./transaction-card.component.css']
})
export class TransactionCardComponent implements OnInit {

  transactions!: Transaction[];
  origenAccountId!: number;
  destinationAccountId!: number;
  description!: string;
  amount!: number;
  nameOwnerDestinationAccount!: string;



  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.getTransactions();
  }

  getTransactions(): void {

    this.transactions = [];
    this.adminService.getTransactions().subscribe(result => {
      for(let i=0; i< result.length ;i++){

        this.origenAccountId = result[i].origenAccountId;
        this.destinationAccountId = result[i].destinationAccountId;
        this.description = result[i].description;
        this.amount = result[i].amount.amount;
        this.nameOwnerDestinationAccount = result[i].nameOwnerDestinationAccount;
        this.transactions.push(new Transaction (this.origenAccountId, this.destinationAccountId, this.description, this.amount, this.nameOwnerDestinationAccount))
      }
      
      this.origenAccountId = 0;
      this.destinationAccountId = 0;
      this.description = "";
      this.amount = 0;
      this.nameOwnerDestinationAccount = "";
    });
  } 

}
