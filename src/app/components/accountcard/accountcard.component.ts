import { AdminService } from 'src/app/services/admin.service';
import { Account } from './../../models/account';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Status } from 'src/app/models/status';


@Component({
  selector: 'app-accountcard',
  templateUrl: './accountcard.component.html',
  styleUrls: ['./accountcard.component.css']
})
export class AccountcardComponent implements OnInit {

  @Input() account!: Account;
  @Input() accountId! :number;
  inputBalance!: number;
  @Output() deleteAccountEvent = new EventEmitter;
/*   @Output() updateBalanceEvent = new EventEmitter; */


  constructor( private adminService: AdminService) { }

  ngOnInit(): void {
  }

  delete(id:number):void{
    this.deleteAccountEvent.emit(this.accountId);
    alert("Account deleted! Please, refresh info");
  }


/*    updateBalance(accountId: number, inputBalance: number){

    console.log(this.inputBalance);
    this.updateBalanceEvent.emit({this.accountId, this.inputBalance});
  }
 */
}
