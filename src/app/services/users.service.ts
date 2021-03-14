import { transition } from '@angular/animations';
import { Checking } from './../models/checking';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../models/transaction';


const API_URL = 'http://localhost:8080/api/test/';


@Injectable({
  providedIn: 'root'
})

export class UsersService {
 
  constructor(private http: HttpClient) {}


  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

   getCheckings(): Observable<CheckingInterface[]> {
    const url =  API_URL + "checking";
    return this.http.get<CheckingInterface[]>(url);
  }
  
  getCheckingsById(id: number): Observable<CheckingInterface[]> {
    const url =  "http://localhost:8080/api/test/checking/by-userid/"+ id;
    return this.http.get<CheckingInterface[]>(url);
  }

  getCreditCardsById(id: number): Observable<CreditCardInterface[]> {
    const url =  "http://localhost:8080/api/test/creditCard/by-userid/"+ id;
    return this.http.get<CreditCardInterface[]>(url);
  }

  getSavingsById(id: number): Observable<SavingsInterface[]> {
    const url =  "http://localhost:8080/api/test/savings/by-userid/"+ id;
    return this.http.get<SavingsInterface[]>(url);
  }

  getAccountsById(id: number): Observable<AccountsInterface[]> {
    const url =  "http://localhost:8090/accounts/by-userId/"+ id;
    return this.http.get<AccountsInterface[]>(url);
  }

  getBalanceById(id: number): Observable <number>{
    const url =  "http://localhost:8090/balance/by-userId/"+ id;
    return this.http.get<number>(url);
  }

  async createTransaction(transaction: Transaction): Promise<void> {
    await this.http.post<Transaction>("http://localhost:8090/transaction", this.body(transaction)).toPromise();
  }

  getAccountById(id:number): Observable <AccountsInterface>{
    const url = "http://localhost:8090/accounts/by-id/" + id;
    return this.http.get<AccountsInterface>(url);
  }

    body(transaction: Transaction): any {

      let transactionBody: any = {

      origenAccountId: transaction.origenAccountId,
      destinationAccountId: transaction.destinationAccountId,
      description: transaction.description,

      amount: {
        amount: transaction.amount
      },
      
      nameOwnerDestinationAccount: transaction.nameOwnerDestinationAccount
      }

      console.log(transactionBody);
      return transactionBody;
    } 
}

 export interface CheckingInterface{

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
status: string,
minimumBalance: number,

 }

 export interface CreditCardInterface{

  id: number,

  balance: {
    currency: string,
    amount: number
},

  user: {
    id  : number,
    username: string,
}

creditLimit: number,
interestRate: number,

 }

 export interface SavingsInterface{

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
status: string,
minimumBalance: number,
interestRate: number,

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





 
