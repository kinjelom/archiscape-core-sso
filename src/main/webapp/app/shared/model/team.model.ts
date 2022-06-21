import { IUser } from 'app/shared/model/user.model';

export interface ITeam {
  id?: string;
  description?: string | null;
  users?: IUser[];
}

export const defaultValue: Readonly<ITeam> = {};
