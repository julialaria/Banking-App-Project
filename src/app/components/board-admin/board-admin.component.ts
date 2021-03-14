import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {

  content?: string;
  currentUser: any;
  selectedAccount: string = '';
  isShown!: boolean;

  constructor(private userService: UsersService, private token: TokenStorageService) { }

  ngOnInit(): void {
    
    this.currentUser = this.token.getUser();
    this.isShown = false; 
    
    /* this.userService.getAdminBoard().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    ); */
  }

  
toggleShow() {
  this.isShown = ! this.isShown;
}

 


}