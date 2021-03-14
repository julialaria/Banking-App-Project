import { Balance } from './../models/balance';
import { Observable } from 'rxjs';
import { CreditCard } from './../models/credit-card';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Checking } from '../models/checking';
import { Saving } from '../models/saving';
import { Status } from '../models/status';
import { Transaction } from '../models/transaction';



@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor (private http: HttpClient) { }

  async createSaving(saving: Saving): Promise<void> {
    await this.http.post<Saving>("http://localhost:8090/create/savings", this.body(saving)).toPromise();
  }

  async createChecking(checking: Checking): Promise<void> {
    await this.http.post<Checking>("http://localhost:8090/create/checking", this.body1(checking)).toPromise();
  }

  async createCreditCard(creditCard: CreditCard): Promise<void> {
    await this.http.post<CreditCard>("http://localhost:8090/create/creditCard", this.body2(creditCard)).toPromise();
  }

  deleteAccount(accountId:number):void{
    this.http.delete('http://localhost:8090/account/'+ accountId).subscribe(data=>
    console.log('Employee deleted'));
  } 

  getAllAccounts():Observable<AccountsInterface[]>{
    return this.http.get<AccountsInterface[]>('http://localhost:8090/accounts');
  }

  getTransactions(): Observable <TransactionsInterface[]>{
    const url = "http://localhost:8090/transactions";
    return this.http.get<TransactionsInterface[]>(url);
  }

  updateBalance(accountId: number, balance:number): void{
    console.log('sending put request');
    this.http.patch('http://localhost:8090/updateBalance/'+ accountId, balance);

  } 

  updateAccount (id: number, balance:number): void {
    this.http.put("http://localhost:8090/updateAccount/" + id , this.body4(balance)).toPromise();
  }
  

  body(saving: Saving): any {
    let savingBody: any = {

      balance: {

        currency: saving.currency,
        amount: saving.balance,
        
      },

     primaryOwner: saving.userId,
     secretKey: saving.secretKey,
     status: saving.status,
     minimumBalance: saving.minimumBalance,
     interestRate: saving.interestRate
    }
    console.log(savingBody);
    return savingBody;
  } 


  body1(checking: Checking): any {
    let checkingBody: any = {

      balance: {

        currency: checking.currency,
        amount: checking.balance,
        
      },

     primaryOwner: checking.userId,
     secretKey: checking.secretKey,
     status: checking.status,
     minimumBalance: checking.minimumBalance,
    
    }
    console.log(checkingBody);
    return checkingBody;
  } 

  body2(creditCard: CreditCard): any {
    let creditCardBody: any = {

      balance: {

        currency: creditCard.currency,
        amount: creditCard.balance,
        
      },

     primaryOwner: creditCard.userId,
     creditLimit: creditCard.creditLimit,
     interestRate : creditCard.interestRate
    
    }
    console.log(creditCard);
    return creditCardBody;
  } 

  body3(status: string): any {

    let statusBody: any = {

      status: status
    }
  }

  body4(balance: number): any{

    let balanceBody: any = {

      balance: balance
    }
  }

}

  export interface AccountsInterface{

    id: number,
  
    balance: {
      currency: string,
      amount: number
  },
  
    user: {
      id  : number,
      username: string,
  }
  
    secretKey: string,
}

export interface TransactionsInterface{

  origenAccountId: number,
  destinationAccountId: number,
  description: string,
  amount: {
    currency: string,
    amount: number,
  },
  nameOwnerDestinationAccount: string

}


  
  
  


