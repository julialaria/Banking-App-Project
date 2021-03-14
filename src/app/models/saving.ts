export class Saving {
   public get userId(): number {
       return this._userId;
   }
   public set userId(value: number) {
       this._userId = value;
   }
   public get interestRate(): number {
       return this._interestRate;
   }
   public set interestRate(value: number) {
       this._interestRate = value;
   }
   public get minimumBalance(): number {
       return this._minimumBalance;
   }
   public set minimumBalance(value: number) {
       this._minimumBalance = value;
   }

   public get status(): string {
       return this._status;
   }
   public set status(value: string) {
       this._status = value;
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
    private _status: string,
    private _minimumBalance: number,
    private _interestRate: number,
    private _id?: number,  
   ){}
}
