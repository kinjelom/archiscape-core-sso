import { ILandscape } from 'app/shared/model/landscape.model';
import { ITeam } from 'app/shared/model/team.model';

export interface IProject {
  id?: number;
  name?: string;
  description?: string | null;
  configuration?: string | null;
  active?: boolean;
  contentStoreUrl?: string | null;
  landscape?: ILandscape;
  team?: ITeam;
}

export const defaultValue: Readonly<IProject> = {
  active: false,
};
