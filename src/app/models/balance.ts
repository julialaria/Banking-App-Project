export class Balance {
    public get balance(): number {
        return this._balance;
    }
    public set balance(value: number) {
        this._balance = value;
    }
    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    constructor(
    private _balance: number,
    private _id: number 
    ) {}
}
