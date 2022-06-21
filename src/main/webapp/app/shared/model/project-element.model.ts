import { IProject } from 'app/shared/model/project.model';
import { ElementType } from 'app/shared/model/enumerations/element-type.model';

export interface IProjectElement {
  id?: number;
  name?: string;
  type?: ElementType;
  documentation?: string | null;
  landscapeElementId?: string | null;
  project?: IProject;
}

export const defaultValue: Readonly<IProjectElement> = {};
