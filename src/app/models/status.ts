export class Status {
    public get status(): string {
        return this._status;
    }
    public set status(value: string) {
        this._status = value;
    }

    constructor(
        private _status: string
    ){}

    
}
