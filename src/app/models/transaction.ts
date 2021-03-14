export class Transaction {
    
    public get nameOwnerDestinationAccount(): string {
        return this._nameOwnerDestinationAccount;
    }
    public set nameOwnerDestinationAccount(value: string) {
        this._nameOwnerDestinationAccount = value;
    }
    public get amount(): number {
        return this._amount;
    }
    public set amount(value: number) {
        this._amount = value;
    }
    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }
    public get destinationAccountId(): number {
        return this._destinationAccountId;
    }
    public set destinationAccountId(value: number) {
        this._destinationAccountId = value;
    }
    public get origenAccountId(): number {
        return this._origenAccountId;
    }
    public set origenAccountId(value: number) {
        this._origenAccountId = value;
    }

    constructor(
        private _origenAccountId: number,
        private _destinationAccountId: number,
        private _description: string,
        private _amount: number,
        private _nameOwnerDestinationAccount: string
        ) {}
}

