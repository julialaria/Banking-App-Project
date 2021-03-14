import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BoardAdminComponent } from './components/board-admin/board-admin.component';
import { BoardUserComponent } from './components/board-user/board-user.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { CheckingCardComponent } from './components/checking-card/checking-card.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { CreditCardCardComponent } from './components/credit-card-card/credit-card-card.component';
import { SavingsCardComponent } from './components/savings-card/savings-card.component';
import { CreateSavingComponent } from './components/create-saving/create-saving.component';
import { CreateCheckingComponent } from './components/create-checking/create-checking.component';
import { CreateCreditCardComponent } from './components/create-credit-card/create-credit-card.component';
import { AccountComponent } from './components/account/account.component';
import { AccountcardComponent } from './components/accountcard/accountcard.component';
import { TransactionComponent } from './components/transaction/transaction.component';
import { TransactionCardComponent } from './components/transaction-card/transaction-card.component';
import { HomeComponent } from './components/home/home.component';
import { BalanceComponent } from './components/balance/balance.component';

@NgModule({
  declarations: [
    AppComponent,
    BoardAdminComponent,
    BoardUserComponent,
    HomeComponent,
    LoginComponent,
    ProfileComponent,
    RegisterComponent,
    CheckingCardComponent,
    CreditCardCardComponent,
    SavingsCardComponent,
    CreateSavingComponent,
    CreateCheckingComponent,
    CreateCreditCardComponent,
    AccountComponent,
    AccountcardComponent,
    TransactionComponent,
    TransactionCardComponent,
    BalanceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
