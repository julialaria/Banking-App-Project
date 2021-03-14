import { CreateSavingComponent } from './components/create-saving/create-saving.component';
import { BoardAdminComponent } from './components/board-admin/board-admin.component';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { BoardUserComponent } from './components/board-user/board-user.component';
import { NgModule } from '@angular/core';
import { CheckingCardComponent } from './components/checking-card/checking-card.component';
import { CreditCardCardComponent } from './components/credit-card-card/credit-card-card.component';
import { SavingsCardComponent } from './components/savings-card/savings-card.component';
import { CreateCheckingComponent } from './components/create-checking/create-checking.component';
import { CreateCreditCardComponent } from './components/create-credit-card/create-credit-card.component';
import { AccountComponent } from './components/account/account.component';
import { TransactionComponent } from './components/transaction/transaction.component';
import { TransactionCardComponent } from './components/transaction-card/transaction-card.component';
import { BalanceComponent } from './components/balance/balance.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'checking', component: CheckingCardComponent },
  { path: 'creditCard', component: CreditCardCardComponent },
  { path: 'saving', component: SavingsCardComponent },
  { path: 'create-saving', component: CreateSavingComponent },
  { path: 'create-checking', component: CreateCheckingComponent },
  { path: 'create-creditCard', component: CreateCreditCardComponent },
  { path: 'account', component: AccountComponent },
  { path: 'transaction', component: TransactionComponent },
  { path: 'transactionCard', component: TransactionCardComponent },
  { path: 'balance', component: BalanceComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
