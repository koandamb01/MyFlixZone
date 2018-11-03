import { Address } from './address';
import { User } from './user';
export class PaymentInfo {
    id: boolean = null;
    ccnumber: string;
    address: Address;
    user: User;
}