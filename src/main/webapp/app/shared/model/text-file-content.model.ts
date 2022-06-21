import dayjs from 'dayjs';

export interface ITextFileContent {
  id?: string;
  version?: number;
  insertDate?: string;
  fileName?: string | null;
  content?: string;
}

export const defaultValue: Readonly<ITextFileContent> = {};
