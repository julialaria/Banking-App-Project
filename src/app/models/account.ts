export class Account {
  public get userId(): number {
      return this._userId;
  }
  public set userId(value: number) {
      this._userId = value;
  }
    
 
  public get secretKey(): string {
      return this._secretKey;
  }
  public set secretKey(value: string) {
      this._secretKey = value;
  }

  public get currency(): string {
      return this._currency;
  }
  public set currency(value: string) {
      this._currency = value;
  }
  public get balance(): number {
      return this._balance;
  }
  public set balance(value: number) {
      this._balance = value;
  }
  public get id(): number {
    return !this._id? -1 : this._id;
}
  public set id(value: number) {
      this._id = value;
  }
     
  constructor(

    private _balance: number,
    private _currency: string,
    private _userId: number,  
    private _secretKey: string,
    private _id?: number, 
    ) {}


  }
