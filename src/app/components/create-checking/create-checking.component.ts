import { Component, OnInit } from '@angular/core';
import { Checking } from '../../models/checking';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-create-checking',
  templateUrl: './create-checking.component.html',
  styleUrls: ['./create-checking.component.css']
})
export class CreateCheckingComponent implements OnInit {

  constructor(private adminService: AdminService) { }

  inputBalanceAmount!: number;
  inputBalanceCurrency!: string;
  inputUserId!: number;
  inputSecretKey!: string;
  inputStatus!: string;
  inputMinimumBalance!: number;

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  ngOnInit(): void {
  }


  async createNewChecking(): Promise<void> {
    const checking: Checking = new Checking(
      this.inputBalanceAmount,
      this.inputBalanceCurrency,
      this.inputUserId,
      this.inputSecretKey,
      this.inputMinimumBalance,
      this.inputStatus,
      );
   
    let response = await this.adminService.createChecking(checking);
    console.log(response);
    alert("Checking created! Remeber than the minimum balance is always 250");
  }

}
