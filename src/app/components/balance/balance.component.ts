import { AdminService } from 'src/app/services/admin.service';
import { Component, OnInit } from '@angular/core';
import { Balance } from 'src/app/models/balance';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.css']
})
export class BalanceComponent implements OnInit {

  inputBalance!: number;
  inputId!: number;
  isSuccessful = false;

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
  }

  async updateAccount(): Promise<void> {
    const balance: Balance = new Balance(
      this.inputId,
      this.inputBalance
      );
   
    let response = await this.adminService.updateAccount(balance.balance, balance.id);
    console.log(response);
    alert("Balance actualized");
    }
}
